package com.caizhixing.poetrywidget

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.caizhixing.poetry.db.PoetryDao
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,PoetryAdapter.RemovePoetryListener{

    private lateinit var mAdapter : PoetryAdapter

    companion object {
         var data :ArrayList<Poetry> = arrayListOf()
    }

    override fun delete(poetry: Poetry) {
        Snackbar.make(rcv,"取消",Snackbar.LENGTH_SHORT)
            .setAction("删除") {
                deletePoetry(poetry)
                data.remove(poetry)
                mAdapter.notifyDataSetChanged()
            }.show()
    }

    override fun deleteAll() {

    }
//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.setting_menu, menu)
//        return true
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        Utils.fixStatusBar(this)
        data.clear()
        data.addAll(queryAllData())
        if(data.size == 0){
            tip_text.visibility = View.VISIBLE
        }
        mAdapter= PoetryAdapter(data)
        mAdapter.setListener(this)
        rcv.adapter = mAdapter
        rcv.layoutManager = LinearLayoutManager(this)
        my_toolbar.setOnMenuItemClickListener {
            menuClick(it)
        }
    }

    private fun menuClick(menuItem: MenuItem) : Boolean{
        return if(R.id.action_search == menuItem.itemId){
            val intent = Intent(this,SettingActivity::class.java)
            startActivity(intent)
            true
        }else{
            false
        }
    }

    private fun getPoetryDao(): PoetryDao {
        return GreenDaoManager.getInstance().session.poetryDao
    }

    private fun deleteAllPotry() {
        getPoetryDao().deleteAll()
    }

    private fun deletePoetry(poetry: Poetry) {
        getPoetryDao().delete(poetry)
    }

//    private fun query(where: String, vararg params: String): List<Poetry> {
//        return getPoetryDao().queryRaw(where, params)
//    }

    private fun save(poetry: Poetry) {
        getPoetryDao().insertOrReplace(poetry)
    }

    private fun queryAllData(): List<Poetry> {
        return getPoetryDao().loadAll()
    }
}

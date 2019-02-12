package com.caizhixing.poetrywidget

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.caizhixing.poetry.db.PoeteryDao
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val data = queryAllData()
        rcv.adapter = PoetryAdapter(data)
        rcv.layoutManager = LinearLayoutManager(this)
    }

    private fun getPoetryDao(): PoeteryDao {
        return GreenDaoManager.getInstance().session.poeteryDao
    }

    private fun deleteAllPotry() {
        getPoetryDao().deleteAll()
    }

    private fun deletePotery(potery: Poetery) {
        getPoetryDao().delete(potery)
    }

//    private fun query(where: String, vararg params: String): List<Poetery> {
//        return getPoetryDao().queryRaw(where, params)
//    }

    private fun save(poetry: Poetery) {
        getPoetryDao().insertOrReplace(poetry)
    }

    private fun queryAllData(): List<Poetery> {
        return getPoetryDao().loadAll()
    }
}

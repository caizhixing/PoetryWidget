package com.caizhixing.poetrywidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import com.caizhixing.poetry.db.PoetryDao
import com.jinrishici.sdk.android.JinrishiciClient
import com.jinrishici.sdk.android.listener.JinrishiciCallback
import com.jinrishici.sdk.android.model.JinrishiciRuntimeException
import com.jinrishici.sdk.android.model.PoetySentence

/**
 * Created on 2019/2/11.
 * Author caizhixing
 */
class OnePoetryWidgetBlack : AppWidgetProvider() {

    companion object {
        val TAG = "OnePoetryWidgetBlack"
        val UPDATE = "update"
    }

    private val client: JinrishiciClient = JinrishiciClient()

    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        if (context == null || appWidgetIds == null) {
            return
        }
        for (id in appWidgetIds.iterator()) {
            val remoteViews = RemoteViews(context.packageName, R.layout.view_poetry_text_widget_black)
            addOnClickListener(context, remoteViews)
            updatePoetry(context, id, remoteViews)
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (context == null || intent == null) {
            return
        }
        if (UPDATE == intent.action) {
            updatePoetry(context)
        }
    }

    private fun addOnClickListener(context: Context, remoteViews: RemoteViews) {
        val intent = Intent(context, javaClass)
        intent.action = UPDATE
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        remoteViews.setOnClickPendingIntent(R.id.poetry_text_view, pendingIntent)
    }

    private fun updatePoetry(context: Context, id: Int, remoteViews: RemoteViews) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        client.getOneSentenceBackground(object : JinrishiciCallback {
            override fun done(poetySentence: PoetySentence?) {
                if (poetySentence != null) {
                    remoteViews.setTextViewText(R.id.poetry_text_view, poetySentence.data.content)
                    appWidgetManager.updateAppWidget(id, remoteViews)
                    Log.d(TAG, "done ${poetySentence.data.content}")
                    saveToDatabase(poetySentence)
                }
            }

            override fun error(e: JinrishiciRuntimeException?) {
                remoteViews.setTextViewText(R.id.poetry_text_view, context.getString(R.string.poetry_example))
                appWidgetManager.updateAppWidget(id, remoteViews)
                Log.d(TAG, "error ${e.toString()}")
            }

        })
    }

    private fun saveToDatabase(poetrySentence: PoetySentence) {
        var poetry = Poetry()
        poetry.displayContent = poetrySentence.data.content
        poetry.title = poetrySentence.data.origin.title
        poetry.dynasty = poetrySentence.data.origin.dynasty
        poetry.author = poetrySentence.data.origin.author
        var stringBuilder = StringBuilder()
        for (str in poetrySentence.data.origin.content) {
            stringBuilder.append(str)
            stringBuilder.append("\n")
        }
        poetry.content = stringBuilder.toString()
        save(poetry)
    }

    private fun getPoetryDao(): PoetryDao {
        return GreenDaoManager.getInstance().session.poetryDao
    }

    private fun save(poetry: Poetry) {
        getPoetryDao().insertOrReplace(poetry)
    }

    private fun updatePoetry(context: Context) {
        Log.d(TAG, "onClick")
        val remoteViews = RemoteViews(context.packageName, R.layout.view_poetry_text_widget_black)
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val componentName = ComponentName(context, OnePoetryWidgetBlack::class.java)
        client.getOneSentenceBackground(object : JinrishiciCallback {
            override fun done(poetySentence: PoetySentence?) {
                if (poetySentence != null) {
                    remoteViews.setTextViewText(R.id.poetry_text_view, poetySentence.data.content)
                    appWidgetManager?.updateAppWidget(componentName, remoteViews)
                    Log.d(TAG, "done ${poetySentence.data.content}")
                    saveToDatabase(poetySentence)
                }
            }

            override fun error(e: JinrishiciRuntimeException?) {
                remoteViews.setTextViewText(R.id.poetry_text_view, context!!.getString(R.string.poetry_example))
                appWidgetManager?.updateAppWidget(componentName, remoteViews)
                Log.d(TAG, "error ${e.toString()}")
            }

        })
    }

}
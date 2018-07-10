package com.example.koutaro.poketeam

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.realm.Realm

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    private lateinit var realm: Realm//.realmクラスのプロパティを取得。この変数は後からonCreate内で初期化するのでlateinit修飾子をつけておく

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        realm = Realm.getDefaultInstance()//realmクラスのインスタンスを取得。これでrealmデータベースを使用する準備ができる。

        val teams = realm.where(Team::class.java).findAll() //データベースにアクセスしてる
        listView.adapter = TeamAdapter(teams)


        fab.setOnClickListener { view ->//パーティ一覧画面からパーティ詳細画面へ遷移
            startActivity<TeamEditActivity>()
        }


        listView.setOnItemClickListener { parent, view, position, id -> //リストの項目を直接タップしたときの処理
            val schedule = parent.getItemAtPosition(position) as Team
            startActivity<TeamEditActivity>( //idがタップされたところのパーティ情報へ飛ぶ
                    "schedule_id" to schedule.id )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()//データベースを閉じる処理
    }
}

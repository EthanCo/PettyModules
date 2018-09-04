package com.heiko.mypadingsample.model

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.heiko.mypadingsample.view.ioThread

/**
 * TODO
 *
 * @author EthanCo
 * @since 2018/9/4
 */
@Database(entities = arrayOf(Gateway::class), version = 1, exportSchema = false)
abstract class GatewayDatabase : RoomDatabase() {
    abstract fun dataBase(): IGetewayModel

    companion object {

        private var instance: GatewayDatabase? = null

        @Synchronized
        fun get(context: Context): GatewayDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                        GatewayDatabase::class.java, "GatewayDatabase2") //GatewayDatabase
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                ioThread {
                                    var db = get(context).dataBase()
                                    db.insert(
                                            CHEESE_DATA.map { Gateway(id = 0, name = it) }
                                    )
                                    for (i in 10..100) {
                                        db.insert(Gateway(0, "GateWay$i"))
                                    }
                                }
                            }
                        }).build()
            }
            return instance!!
        }
    }
}

private val CHEESE_DATA = arrayListOf(
        "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
        "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale",
        "Aisy Cendre", "Allgauer Emmentaler", "Alverca", "Ambert", "American Cheese",
        "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh", "Anthoriro", "Appenzell",
        "Aragon", "Ardi Gasna", "Ardrahan", "Armenian String", "Aromes au Gene de Marc",
        "Asadero", "Asiago", "Aubisque Pyrenees", "Autun", "Avaxtskyr", "Baby Swiss",
        "Babybel", "Baguette Laonnaise", "Bakers", "Baladi", "Balaton", "Bandal", "Banon",
        "Barry's Bay Cheddar", "Basing", "Basket Cheese", "Bath Cheese", "Bavarian Bergkase",
        "Baylough", "Beaufort", "Beauvoorde", "Beenleigh Blue", "Beer Cheese", "Bel Paese",
        "Bergader", "Bergere Bleue", "Berkswell", "Beyaz Peynir", "Bierkase", "Bishop Kennedy",
        "Blarney", "Bleu d'Auvergne", "Bleu de Gex", "Bleu de Laqueuille",
        "Bleu de Septmoncel", "Bleu Des Causses", "Blue", "Blue Castello", "Blue Rathgore",
        "Blue Vein (Australian)", "Blue Vein Cheeses", "Bocconcini", "Bocconcini (Australian)"
)
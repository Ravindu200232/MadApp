package com.example.myapplication

object DataObject {

    var listdata= mutableListOf<CardInfo>()

    fun setData(title: String,priority: String,Rdate:String,Rtime:String){

        listdata.add(CardInfo(title,priority,Rdate,Rtime))
    }

    fun getAllData():List<CardInfo>
    {
        return listdata
    }

    fun deleteAll(){
        listdata.clear()
    }

}
package com.example.quizapp

object constants{
    fun getquestions(): ArrayList<questions>{
    val questionslist= ArrayList<questions>()
        val que1= questions(1,"This vegetarian culinary dish is found mainly in which Indian state?",R.drawable.unnamed,"Uttar Pradesh","Uttarakhand","Gujarat","Haryana",3)
        questionslist.add(que1)
        val que2= questions(2,"Which of these Indian dishes can cure mouth ulcers?",R.drawable.istockphoto_922783734_612x612,"Pallak Paneer","Aloo Bonda","Sathu Paratha","Pani Puri",4)
        questionslist.add(que2)
        val que3 = questions(3,"Which of the following is the most healthy Meat?",R.drawable.emerson_vieira_cpkpj_u9eum_unsplash,"Buffalo","Pork","Turkey","Fish",1)
        questionslist.add(que3)
        val que4 = questions(4,"Which of the following have the highest Vitamin C content?",R.drawable.dan_cristian_padure_miyzdphuyy0_unsplash,"Grapes","Peppers","Oranges","Brinjals",2)
        questionslist.add(que4)
        val que5 = questions(5,"what is the other name for Cilantro?",R.drawable.dose_juice_stpy_oea3h0_unsplash,"Parsley","Corriander","Watercress","Kale",2)
        questionslist.add(que5)
        val que6 = questions(6,"Which is the most stolen food in the world?",R.drawable.cat_thief_funny_animal_pictures_45__605,"Apples","Olives","Cheese","Tomatoes",3)
        questionslist.add(que6)
        val que7 =questions(7,"How many drink combinations are there at Starbucks?",R.drawable.kevser__o3ddgs0h6c_unsplash,"87000","47000","970","70",1)
        questionslist.add(que7)
        val que8 = questions(8,"Honey is made of which of the following?",R.drawable.roberta_sorge_kp9uvn_puac_unsplash,"Nectar","Bee's saliva","Bee's poop","Bee's vomit",3)
        questionslist.add(que8)
        val que9 = questions(9,"Coca Cola use to be made of which of the following?",R.drawable.laura_chouette_q1zleujip1y_unsplash,"Tar","Goat Mucus","Cocaine","Black Peppers",3)
        questionslist.add(que9)
        val que10 = questions(10,"How many teaspoons of nutmeg could potentially kill you?",R.drawable.jocelyn_morales_1cxndpamdoc_unsplash,"20","12","5","2",4)
        questionslist.add(que10)
        return questionslist
    }
}
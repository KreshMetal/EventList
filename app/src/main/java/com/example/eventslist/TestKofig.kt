package com.example.eventslist

val TEST_SESSION = Session("19 апреля", "Доклад: Краткий экскурс в мир многопоточности","1","https://static.tildacdn.com/tild3432-3435-4561-b136-663134643162/photo_2021-04-16_18-.jpg",false,"Степан Чурюканов","10:00-11:00")
val TEST_FAVORITE_LIST = FavoriteList(listOf<Session?>(TEST_SESSION, TEST_SESSION, TEST_SESSION))
val TEST_SESSIONS_LIST = listOf<Session>(TEST_SESSION, Session("21 апреля","Доклад: Всё, что нужно знать о корутинах", "5", "https://static.tildacdn.com/tild6364-6664-4235-b731-333261663338/photo_2021-04-16_18-.jpg", false, "Алексей Панов", "10:00-11:00"), TEST_SESSION)
//package com.restaurant.exam
//
//import android.annotation.SuppressLint
//import com.google.firebase.messaging.FirebaseMessagingService
//import com.google.firebase.messaging.RemoteMessage
//
//@SuppressLint("MissingFirebaseInstanceTokenRefresh")
//class MyFirebaseMessagingService : FirebaseMessagingService() {
//
//    val TAG = "MyFirebaseMessagingService"
//
//
//    @SuppressLint("LongLogTag")
//    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        super.onMessageReceived(remoteMessage)
//        handleNotification(remoteMessage)
//    }
//
//    private fun handleNotification(remoteMessage: RemoteMessage){
////        val data = Gson().fromJson(
////            remoteMessage.data["result"].toString(),
////        )
////        val title = data.title
////        val content = data.content
////        val type = data.objectType?.value
////        val idMessage = data.objectId
////        val idNotice= data.id
////
////        val intentBroadCast = Intent()
////        intentBroadCast.action = "broadcast.Notification"
////        intentBroadCast.putExtra(MESSAGE_ID, idMessage)
////        intentBroadCast.putExtra(NOTICE_ID, idNotice)
////        intentBroadCast.putExtra(NOTICE_TITLE, title)
////        intentBroadCast.putExtra(NOTICE_CONTENT, content)
////        intentBroadCast.putExtra(NOTICE_TYPE, type)
////        sendBroadcast(intentBroadCast)
////
////        val notificationUtils =
////            NotificationUtils(applicationContext)
////
//
//    }
//
//}

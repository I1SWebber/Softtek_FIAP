package br.com.fiap.softtek.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

fun criarCanalDeNotificacao(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val canal = NotificationChannel(
            "checkin_channel",
            "Lembretes do Check-in",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "Notificações diárias para lembrar do check-in emocional"
        }

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(canal)
    }
}

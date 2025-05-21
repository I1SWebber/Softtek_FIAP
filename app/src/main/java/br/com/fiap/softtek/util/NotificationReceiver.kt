package br.com.fiap.softtek.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import br.com.fiap.softtek.R
import java.util.Calendar

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val builder = NotificationCompat.Builder(context, "checkin_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Hora do Check-in 🌿")
            .setContentText("Como você está se sentindo hoje?")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Verifica a permissão antes de notificar
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            with(NotificationManagerCompat.from(context)) {
                notify(1, builder.build())
            }
        }
    }
}

fun agendarNotificacaoDiaria(context: Context) {
    val intent = Intent(context, NotificationReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )

    val calendar = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.HOUR_OF_DAY, 10)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
    }

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarmManager.setRepeating(
        AlarmManager.RTC_WAKEUP,
        calendar.timeInMillis,
        AlarmManager.INTERVAL_DAY,
        pendingIntent
    )
}


fun dispararNotificacaoAgora(context: Context) {
    val builder = NotificationCompat.Builder(context, "checkin_channel")
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle("Hora do Check-in 🌿")
        .setContentText("Como você está se sentindo agora?")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        with(NotificationManagerCompat.from(context)) {
            notify(99, builder.build()) // ID 99 pra não conflitar com o agendado
        }
    }
}



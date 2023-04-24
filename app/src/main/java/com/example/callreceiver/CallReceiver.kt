package com.example.callreceiver

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.view.WindowManager
import android.widget.Toast

class CallReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
            if (state == TelephonyManager.EXTRA_STATE_RINGING) {
                Toast.makeText(context, "전화가 왔습니다.", Toast.LENGTH_SHORT).show()
                context?.let { showAlertDialog(it) }
            }
        }
    }
    private fun showAlertDialog(context: Context) {
        val dialog = AlertDialog.Builder(context.applicationContext)
            .setTitle("알림")
            .setMessage("전화 도중에는 음성채팅이 불가능합니다.")
            .setPositiveButton("확인") { _, _ -> }
            .create()
        dialog.window?.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)
        dialog.show()
    }
}
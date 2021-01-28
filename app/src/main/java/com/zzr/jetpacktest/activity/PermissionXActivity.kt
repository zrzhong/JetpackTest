package com.zzr.jetpacktest.activity

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.permissionx.guolindev.PermissionX
import com.zzr.jetpacktest.R
import com.zzr.jetpacktest.databinding.ActivityPermissionXBinding

class PermissionXActivity : BaseActivity<ActivityPermissionXBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_permission_x)

        binding.requestPermission.setOnClickListener {
            PermissionX.init(this)
                .permissions(
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.CAMERA,
                    Manifest.permission.CALL_PHONE
                )
                .explainReasonBeforeRequest()
                .onExplainRequestReason { scope, deniedList, beforeRequest ->
                    scope.showRequestReasonDialog(
                        deniedList,
                        "Core fundamental are based on these permissions",
                        "OK",
                        "Cancel"
                    )
                }
                .onForwardToSettings { scope, deniedList ->
                    scope.showForwardToSettingsDialog(
                        deniedList,
                        "You need to allow necessary permissions in Settings manually",
                        "OK",
                        "Cancel"
                    )

                }
                .request { allGranted, grantedList, deniedList ->
                    if (allGranted) {
                        Toast.makeText(this, "All permissions are granted", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(
                            this,
                            "These permissions are denied: $deniedList",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

    }
}
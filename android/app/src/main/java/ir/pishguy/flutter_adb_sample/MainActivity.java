package ir.pishguy.flutter_adb_sample;

import android.os.Bundle;
import android.provider.Settings;

import androidx.annotation.NonNull;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {
  private String CHANNEL = "adb";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    GeneratedPluginRegistrant.registerWith(this);

    new MethodChannel(flutterView, CHANNEL).setMethodCallHandler(
            (call, result) -> {//java 8 lamda..
              if (call.method.equals("checkingadb")) {
                checkingadb(call, result);
              } else {
                result.notImplemented();
              }
            }
    );
  }

  private void checkingadb(MethodCall call, MethodChannel.Result result) {
    if (Settings.Secure.getInt(this.getContentResolver(), Settings.Secure.ADB_ENABLED, 0) == 1) {
      // debugging enabled
      result.success(1);
    } else {
      //;debugging does not enabled
      result.success(0);
    }
  }
}

package com.example.websocket;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    String token = "Bearer%20eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJoREkwZ2hyVlJvaE5zVy1wSXpZeDBpT2lHMzNlWjJxV21sRk4wWGE1dWkwIn0.eyJleHAiOjE3MDE5NDA3MjgsImlhdCI6MTcwMTg1NjgxOSwiYXV0aF90aW1lIjoxNzAxODU0MzI4LCJqdGkiOiJmMzA0MTNlMy0wZTg1LTQ0MTgtOTc0NS05MTM0ZDZlZjEzNGMiLCJpc3MiOiJodHRwczovL3Vpb3QuaXh4Yy5kZXYvYXV0aC9yZWFsbXMvbWFzdGVyIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjRlM2E0NDk2LTJmMTktNDgxMy1iZjAwLTA5NDA3ZDFlZThjYiIsInR5cCI6IkJlYXJlciIsImF6cCI6Im9wZW5yZW1vdGUiLCJub25jZSI6Ijk0ZTZiNmNjLTkzZDUtNDJlOS04ZTY3LTBjYzJhNmZiZDg2NiIsInNlc3Npb25fc3RhdGUiOiIwZTg2MGQwNC03ZjJmLTQ5NzMtYTJhZC0wZDA2NmFkYjUwYjciLCJhY3IiOiIwIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHBzOi8vdWlvdC5peHhjLmRldiJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiZGVmYXVsdC1yb2xlcy1tYXN0ZXIiLCJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsib3BlbnJlbW90ZSI6eyJyb2xlcyI6WyJyZWFkOm1hcCIsInJlYWQ6cnVsZXMiLCJyZWFkOmluc2lnaHRzIiwicmVhZDphc3NldHMiXX0sImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwiLCJzaWQiOiIwZTg2MGQwNC03ZjJmLTQ5NzMtYTJhZC0wZDA2NmFkYjUwYjciLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJGaXJzdCBOYW1lIExhc3QgbmFtZSIsInByZWZlcnJlZF91c2VybmFtZSI6InVzZXIiLCJnaXZlbl9uYW1lIjoiRmlyc3QgTmFtZSIsImZhbWlseV9uYW1lIjoiTGFzdCBuYW1lIiwiZW1haWwiOiJ1c2VyQGl4eGMuZGV2In0.ROa2UNN8sI22CNNXgwEkViKN8Zym1sBDHM6krWvmV2LuR33gXEBwnpnD97yvDxWjnBq_21s3B1O0ON4mbFRiQ4a7kw56GZXRfl0mxK9QphDCBtAiIZN0xKYZYTkdxzcBATG8aFVu6qqQN-P97sGkFz4QxzMdAsXxJLkN9GlmdsXJWstaV-M1wf_33JqZwMKH6uCZsuy0YUgix_MBvogQWzL_bUDw5P4-lVrVG8oD1uwWEvMBI3YwMGsM3ko9SDfxV4oXNs2K-9Uw3HU89VT_87MbpHx3bV6R_0UCojY53iPPegBlQ-M-Sq8_dca3MRERyLjhDli3c6AvEzFZ5ytNcQ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            MyWebSocketClient client = new MyWebSocketClient("ws://uiot.ixxc.dev/websocket/events?Realm=master&Authorization="+token);
            client.connect();
        } catch (URISyntaxException e) {
            Log.d("WebSocket",e.getMessage());
        }
        Log.d("WebSocket",msg)
    }
    public class MyWebSocketClient extends WebSocketClient {

        public MyWebSocketClient(String serverUrl) throws URISyntaxException {
            super(new URI(serverUrl));
        }
        @Override
        public void onOpen(ServerHandshake handshake) {
            try {
                Log.d("WebSocket","Connected Successfully");
                send("REQUESTRESPONSE:{\"messageId\":\"read-assets:5zI6XqkQVSfdgOrZ1MyWEf:AssetEvent2\",\"event\":{\"eventType\":\"read-assets\",\"assetQuery\":{\"ids\":[\"5zI6XqkQVSfdgOrZ1MyWEf\"]}}}\t");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        @Override
        public void onMessage(String message) {
            TextView textView = findViewById(R.id.text);
            textView.setText(message);
        }
        @Override
        public void onClose(int code, String reason, boolean remote) {
            Log.d("WebSocket","Wss Close");
        }
        @Override
        public void onError(Exception ex) {
            Log.d("WebSocket",ex.getMessage());
        }
    }
}

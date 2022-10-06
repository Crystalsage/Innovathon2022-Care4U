package com.example.innovathon2022_care4u;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    WebSocketClient ws_client_1,ws_client_2;
    WebSocketServer ws_stuff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.websocket_tests);

        TextView ws_status = findViewById(R.id.websocket_status);

        Button client_1_btn = findViewById(R.id.client_1);
        Button client_2_btn = findViewById(R.id.client_2);

        // Start the server
        try {
            Log.i("WS SERVER: ", "Trying to start the WS server");
            ws_stuff = new WS4U_Server(9001);
            ws_stuff.start();
            ws_status.setText("Started server!");

        } catch (UnknownHostException e) {
            Log.i("WS SERVER: ", "Epic fail");
            e.printStackTrace();
        }


        Toast.makeText(MainActivity.this,"Connecting client 1!", Toast.LENGTH_SHORT).show();
        try {
            ws_client_1 = new WS4u_Client(new URI("ws://localhost:9001"));
            ws_client_1.connectBlocking();
        } catch (InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }

        // When text is clicked, run the client to say hello
        client_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ws_client_1.send("Client 1 here!");
            }
        });

        client_2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ws_stuff.broadcast("Server here!");
            }
        });
    }
}
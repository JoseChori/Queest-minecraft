package com.queest.plugin;

import org.bukkit.command.Command;

import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

import org.apache.http.client.methods.CloseableHttpResponse;

import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.HttpClients;

import org.apache.http.util.EntityUtils;

public class QueestPlugin extends JavaPlugin {

    @Override

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("queest")) {

            if (sender instanceof Player) {

                Player player = (Player) sender;

                String response = "";

                try {

                    response = comunicarseConLaAPI();

                } catch (IOException e) {

                    player.sendMessage("Error al comunicarse con la API. Por favor inténtelo de nuevo más tarde.");

                    return true;

                }

                player.sendMessage(response);

            } else {

                String response = "";

                try {

                    response = comunicarseConLaAPI();

                } catch (IOException e) {

                    sender.sendMessage("Error al comunicarse con la API. Por favor inténtelo de nuevo más tarde.");

                    return true;

                }

                sender.sendMessage(response);

            }

            return true;

        }

        return false;

    }

    private String comunicarseConLaAPI() throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("https://api.example.com/pregunta");

        CloseableHttpResponse response = httpClient.execute(httpGet);

        HttpEntity entity = response.getEntity();

        String responseString = Entity.toString(entity, "UTF-8");

        httpClient.close();

        return responseString;

    }

}

package com.team.dream.sleepsafe.utils;

import com.google.firebase.messaging.RemoteMessage;

interface INotificationsService {
    void sendNotification(String notificationTitle, String notificationBody, String dataTitle, String dataMessage);
    void onMessageReceived(RemoteMessage remoteMessage);
}

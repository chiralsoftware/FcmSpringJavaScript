# FcmSpringJavaScript
Sample project using a Spring Java server to send messages to a JavaScript Firebase Cloud Messaging client

# Generate and get FCM credentials

You will need to go through the steps to enable FCM in your Firebase console: https://console.firebase.google.com/

From that, add Web as a type of client, and save the credentials JSON file. Also save the client credentials JavaScript block.
        
Store the server JSON credential file in a convenient place where the server process can read it. Then edit the FcmConfig class and set the path to the credentials file. Also in the configure() method change the database URL as appropriate.

Next edit the fcm.html file, in the script section, to set the config var based on the values from the Firebase console.

At this point, the system should be ready to use. Build it as a WAR, run it on Tomcat, and go to it in your browser.

View the console and you will see that the ServiceWorker is receiving the messages that are being sent by the scheduled method on the server. This all works on localhost.

# ServiceWorker instead of firebase-messaging-sw.js

What's different about this project? Most projects using Firebase Cloud Messaging use a magic file called called firebase-messaging-sw.js, which must be in the root path. The documentation says you can use messaging.useServiceWorker(ServiceWorkerRegistration) but doesn't show  examples of how. This project is a minimalist complete demonstration of how it all works.

# Next

The Service Worker receives the notification in the push event, and shows it on the console, but doesn't do anything else with it. Real applications will either do something in the background or show a notification to the user. Many other articles show how to do those things.

This example sends a message every 10 seconds. Real applications should not annoy users with notifications so often, although if the window is in the foreground, something like a chat app could display notifications that quickly.

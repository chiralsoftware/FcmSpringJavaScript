# FcmSpringJavaScript
Sample project using a Spring Java server to send messages to a JavaScript Firebase Cloud Messaging client

# Generate and get FCM credentials

You will need to go through the steps to enable FCM in your Firebase console: https://console.firebase.google.com/

From that, add Web as a type of client, and save the credentials JSON file. Also save the client credentials JavaScript block.
        
Store the server JSON credential file in a convenient place where the server process can read it. Then edit the FcmConfig class and set the path to the credentials file. Also in the configure() method change the database URL as appropriate.

Next edit the fcm.html file, in the script section, to set the config var based on the values from the Firebase console.

At this point, the system should be ready to use. Build it as a WAR, run it on Tomcat, and go to it in your browser.

View the console and you will see that the ServiceWorker is receiving the messages that are being sent by the scheduled method on the server. This all works on localhost. It also all works from a non-root context if desired.

# ServiceWorker instead of firebase-messaging-sw.js

What's different about this project? Most projects using Firebase Cloud Messaging use a magic file called called firebase-messaging-sw.js, which must be in the root path. The documentation says you can use messaging.useServiceWorker(ServiceWorkerRegistration) but doesn't show  examples of how. This project is a minimalist complete demonstration of how it all works. Google's documentation isn't clear on the necessity of firebase-messaging-sw.js, or how to use a ServiceWorker to avoid needing firebase-messaging-sw.js. This example shows clearly how to do that, without much other code and no frameworks.

# Next

The Service Worker receives the notification in the push event, and shows it on the console, but doesn't do anything else with it. Real applications will either do something in the background or show a notification to the user. Many other articles show how to do those things.

This example sends a message every 10 seconds. Real applications should not annoy users with notifications so often, although if the window is in the foreground, something like a chat app could display notifications that quickly.

# Not a complete project

This project does almost no error checking. It's greatly simplified to show the minimum code needed to send messages from a Java web server to JavaScript clients by FCM. Errors occur when a client token is removed, and sending a message throws an exception. Also the user might not allow notifications, which also should stop the process of trying to send a token. 

The server needs to get the cient's token to be able to address the client. For that, we have the client send a simple POST to a controller which keeps a Set of tokens. This is greatly simplified from what a deployed system would do.

FCM allows sending broadcasts to topics, but broadcast to a topic can't be received on JavaScript clients. JavaScript clients must be addressed individually, by token.

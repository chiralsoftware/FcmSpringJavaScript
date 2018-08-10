// ServiceWorker to handle push events

addEventListener('push', event => {
    console.log("Received an event: " + event.data.text());
});

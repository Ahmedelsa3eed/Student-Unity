importScripts('https://www.gstatic.com/firebasejs/9.1.3/firebase-app-compat.js');
importScripts('https://www.gstatic.com/firebasejs/9.1.3/firebase-messaging-compat.js');

firebase.initializeApp({
    apiKey: 'config data from general tab',
    authDomain: 'config data from general tab',
    databaseURL: 'config data from general tab',
    projectId: 'config data from general tab',
    storageBucket: 'config data from general tab',
    messagingSenderId: 'config data from general tab',
    appId: 'config data from general tab',
    measurementId: 'config data from general tab',
});
const messaging = firebase.messaging();

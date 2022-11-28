// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  baseUrl: 'http://localhost:8080',
  dummyUsers: [
      {
        "studentId": 1,
        "name": "Leanne Graham",
        "email": "abc@cde.fg",
        "password":"123456",
        "role": "admin",
        "sessionID": "1234567890"
      },
      {
        "studentId": 2,
        "name": "Ervin Howell",
        "email": "aaaa@gmail.com",
        "password":"123456",
        "role": "user",
        "sessionID": "1234567890"
      },
      {
        "studentId": 3,
        "name": "Clementine Bauch",
        "email": "aaqwq@aadsasd.aada",
        "password":"123456",
        "role": "user",
        "sessionID": "1234567890"
      },
      {
        "studentId": 4,
        "name": "Patricia Lebsack",
        "email": "adsads@gmail.com",
        "password":"11",
        "role": "user",
        "sessionID": "1234567890"
      },
      {
        "studentId": 5,
        "name": "Chelsey Dietrich",
        "email": "adsads@ddd.dd",
        "password":"sdd",
        "role": "user",
        "sessionID": "1234567890"
      },
      {
        "studentId": 6,
        "name": "Mrs. Dennis Schulist",
        "email": "sasas.eee@dsdsd.com",
        "password":"qqqq" ,
        "role": "user",
        "sessionID": "1234567890"
      },
      {
        "studentId": 7,
        "name": "Kurtis Weissnat",
        "email": "abc@gmail.com",
        "password":"sadads",
        "role": "user",
        "sessionID": "1234567890"
      }]
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.

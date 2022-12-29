// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

import { Announcement } from './../app/models/Announcement';

export const environment = {
    production: false,
    firebase: {
        apiKey: "AIzaSyCi1YbjhedS--Q9_1TMc0vnLQ6LkkKa9ck",
        authDomain: "student-unity-774ae.firebaseapp.com",
        projectId: "student-unity-774ae",
        storageBucket: "student-unity-774ae.appspot.com",
        messagingSenderId: "958079856699",
        appId: "1:958079856699:web:c169cfc9bdea71e24fac1e",
        measurementId: "G-87M5B8HLLD",
        vapidKey: "BFes-UDf4Mk6dRHczBPIqZOZWNF6qDRBuH3Wml4v4LWTvYj14lhVVFmdECcXGaIheTvIDkF40cAo40ttBVkTmfY"
      },
    baseUrl: 'http://localhost:8090',
    annouc: [
        {
            courseName: 'SWE',
            body: 'swe body',
            postedDate: '2012-12-12',
        },
        {
            courseName: 'AI',
            body: 'AI body',
            postedDate: '2022-10-10',
        },
    ] as Announcement[],
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.

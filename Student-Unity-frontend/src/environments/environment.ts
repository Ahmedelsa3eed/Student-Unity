// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

import { STATUS } from 'src/app/models/Status';

export const environment = {
  production: false,
  baseUrl: 'http://localhost:8090',
  completedtasks: [
    {
      taskId: '1',
      title: 'task 1',
      courseCode: 'EEE1',
      dueDate: '12/15/2022',
      telegramLink: 'https::/telegram/task1',
      status: STATUS.DONE,
    },
    {
      taskId: '3',
      title: 'task 3',
      courseCode: 'ECC1',
      dueDate: '12/15/2022',
      telegramLink: 'https::/telegram/task1',
      status: STATUS.DONE,
    },
  ],
  notCompletedTasks: [
    {
      taskId: '2',
      title: 'task 2',
      courseCode: 'CE1',
      dueDate: '13/15/2022',
      telegramLink: 'https::/telegram/task1',
      status: STATUS.TODO,
    },
    {
      taskId: '4',
      title: 'task 4',
      courseCode: 'CEE2',
      dueDate: '13/15/2022',
      telegramLink: 'https::/telegram/task1',
      status: STATUS.TODO,
    },
  ],
  courses: ['AI', 'Networks', 'SWE'],
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.

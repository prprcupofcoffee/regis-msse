import { InMemoryDbService } from 'angular-in-memory-web-api';
import { CourseType } from '../app/course-type';

export class MeetsDataService implements InMemoryDbService {
    createDb() {
        const meets = [
            {
                id: 1,
                name: 'CUDA',
                date: new Date(2017, 6, 26),
                location: 'Northtown Aquatic Center',
                courseType: CourseType.ShortCourseYards
            },
            {
                id: 2,
                name: 'High Point',
                date: new Date(2017, 7, 156),
                location: 'CHHS',
                courseType: CourseType.ShortCourseYards
            }
        ];

        return { meets };
    }
}
import { InMemoryDbService } from 'angular-in-memory-web-api';
import { CourseType } from './course-type';

export class InMemoryDataService implements InMemoryDbService {
    createDb() {
        const meets = [
            {
                name: 'CUDA',
                date: new Date(2017, 6, 26),
                location: 'Northtown Aquatic Center',
                courseType: CourseType.ShortCourseYards
            },
            {
                name: 'High Point',
                date: new Date(2017, 7, 156),
                location: 'CHHS',
                courseType: CourseType.ShortCourseYards
            }
        ];

        return { meets };
    }
}
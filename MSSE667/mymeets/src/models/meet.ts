import { CourseType } from '../app/course-type';

export class Meet {
    constructor(
        public name: string,
        public date: Date,
        public location: string,
        public courseType: CourseType
    ) {
        
    }
}

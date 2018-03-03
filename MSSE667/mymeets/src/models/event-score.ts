import { EventType } from '../app/event-type';
import { Meet } from './meet';

// Defines the different types of swim meet courses.
//
export class EventScore {
    event: EventType;       // type of event competed
    length: number;         // number of yards or meters
    score: number           // time to complete event, in 100ths of a second
    meet: Meet;             // meet where the score was achieved
}

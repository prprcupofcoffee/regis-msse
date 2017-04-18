<?php
/**
 * Encapsulate information about a Lesson
 *
 * @author david
 */
class Lesson implements JsonSerializable {

    private $id = '', 
            $title = '',
            $url = '';

    public function __construct($pId, $pTitle, $pUrl) {
        $this->id = $pId;
        $this->title = $pTitle;
        $this->url = $pUrl;
    }

    public function jsonSerialize() {
        return [
            'id' => $this->id,
            'title' => $this->title,
            'url' => $this->url
        ];
    }
}

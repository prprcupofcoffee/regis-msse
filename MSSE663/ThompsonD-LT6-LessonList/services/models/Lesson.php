<?php
/**
 * Encapsulate information about a Lesson
 *
 * @author david
 */
class Lesson implements JsonSerializable {

    private $id = '',
            $img = '',
            $title = '',
            $url = '';

    public function __construct($pId, $pImg, $pTitle, $pUrl) {
        $this->id = $pId;
        $this->img = $pImg;
        $this->title = $pTitle;
        $this->url = $pUrl;
    }

    public function jsonSerialize() {
        return [
            'id' => $this->id,
            'img' => $this->img,
            'title' => $this->title,
            'url' => $this->url
        ];
    }
}

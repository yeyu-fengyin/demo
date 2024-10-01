package com.example.common.event

import jdk.jfr.Event
import lombok.Data


/**
 *
 *@Auth yeyu
 *@Date 2024/9/30
 *
 **/
@Data
class MyEvent: Event() {
    var myField: String = "Hello, world!"
}
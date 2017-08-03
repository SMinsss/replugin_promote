/*
 * Copyright 2015-present wequick.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.ssjjsy.gradle.util

import static org.fusesource.jansi.Ansi.*
import static org.fusesource.jansi.Ansi.Color.*

public final class AnsiUtils {
    public static def red(text) {
        return org.fusesource.jansi.Ansi.ansi().fg(org.fusesource.jansi.Ansi.Color.RED).a(text).reset()
    }

    public static def green(text) {
        return org.fusesource.jansi.Ansi.ansi().fg(org.fusesource.jansi.Ansi.Color.GREEN).a(text).reset()
    }

    public static def white(text) {
        return org.fusesource.jansi.Ansi.ansi().fg(org.fusesource.jansi.Ansi.Color.WHITE).a(text).reset()
    }

    public static def yellow(text) {
        return org.fusesource.jansi.Ansi.ansi().fg(org.fusesource.jansi.Ansi.Color.YELLOW).a(text).reset()
    }

    public static def bold(text) {
        return org.fusesource.jansi.Ansi.ansi().bold().a(text).reset()
    }
}
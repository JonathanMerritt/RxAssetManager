/*
 *     Copyright 2018 Jonathan Merritt 11R00TT00R11@GMAIL.COM
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.github.jonathanmerritt.rxassetmanager.core;

import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import io.reactivex.functions.Function;
import java.io.File;
import java.io.InputStream;

import static com.google.common.io.Files.getFileExtension;
import static java.lang.String.format;

public final class Utilities {
  public static ByteSource byteSource(InputStream stream) {
    return new ByteSource() {
      @Override public InputStream openStream() {
        return stream;
      }
    };
  }

  public static Function<byte[], File> saveFile(String fileName, String saveFolder) {
    return bytes -> {
      final File file = new File(format("%s/%s", saveFolder, fileName));
      Files.createParentDirs(file);
      Files.write(bytes, file);
      return file;
    };
  }

  public static boolean isFile(String path) {
    return !getFileExtension(path).isEmpty();
  }

  public static boolean isXml(String fileName) {
    return getFileExtension(fileName).equals("xml");
  }

  public static String mapPath(String folderName, String name) {
    final String path = String.format("%s/%s", folderName, name);
    return path.length() > 1 && path.substring(0, 1).equals("/") ? path.replace(path.substring(0, 1), "") : path;
  }

  static <T> T checkNotNull(T t) {
    if (t == null) throw new RuntimeException("Object is null!");
    return t;
  }
}

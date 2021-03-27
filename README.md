# LoadingButton

*LoadingButton*:  an easy button with loading state for Android

[![](https://jitpack.io/v/ValerioPapi/LoadingButton.svg)](https://jitpack.io/#ValerioPapi/LoadingButton) [![](https://img.shields.io/badge/language-Kotlin-green.svg)](https://github.com/ValerioPapi/LoadingButton) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)


<img src="https://raw.githubusercontent.com/ValerioPapi/LoadingButton/main/art/button_anim.gif" width="600" />

---

### Getting Started

Add to your root build.gradle :

```groovy
allprojects {
  repositories {
      ...
      maven { url 'https://jitpack.io' }
    }
  }
```

Add the dependency :

```groovy
dependencies {
    implementation 'com.github.ValerioPapi:LoadingButton:{version}'
}
```


###Tables

| Attribute  | type  |
| :------------ |:---------------:|
| text  | string |
| textSize  | dimension |
| textColor  | color |
| cornerRadius | dimension  |
| loadingText  | string |
| successText  | string        |
| errorText | string     |
| backgroundTint | color  |
| successBackgroundTint | color  |
| errorBackgroundTint | color  |
| restoreTimeMillis | color  |
| successIcon | reference  |
| progressBarSize | dimension  |
| progressGravity | start, center, end  |
| progressPadding | none, onlyProgress, both  |





# License

	LoadingButton
    Copyright 2021 ValerioPapi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

---

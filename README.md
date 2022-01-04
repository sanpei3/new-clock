CURRENTLY UNDER DEVELOPMENT

# The best open source clock app out there. App includes alarm,clock, timer,stopwatch. Updated version of the aosp Kotlin deskclock.

# based from :
* build enviroment : GitHub [qw123wh/new-clock](https://github.com/qw123wh/new-clock)
* application code: [AOSP Deskclock(kotlin version)](https://android.googlesource.com/platform/packages/apps/DeskClock/+/a0b1d03354e46c2d505adf35966d5fbcc207a980/src/com/android/deskclock/)

# New Features (compare from AOSP version):
* ALARM: exclude holidayes

# Known Issues
* Holiday infomation is hardcoded at [source file](https://github.com/sanpei3/new-clock/blob/master/src/com/android/deskclock/data/Holidays.kt)
* If user push "Exclude Holidayes" button, however it does not effect alarm setting. Currently user have to push "week of day button".

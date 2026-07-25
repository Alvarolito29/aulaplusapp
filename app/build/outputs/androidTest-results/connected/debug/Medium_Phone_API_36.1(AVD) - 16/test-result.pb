
ä
V
ExampleInstrumentedTestcom.example.pruebauseAppContext2Ù²ƒÊ€ Â:Ù²ƒÊ€Œû)"ã

logcatandroidÍ
ÊC:\Users\juerz\AndroidStudioProjects\aulaplusapp\app\build\outputs\androidTest-results\connected\debug\Medium_Phone_API_36.1(AVD) - 16\logcat-com.example.prueba.ExampleInstrumentedTest-useAppContext.txt"³

device-infoandroid˜
•C:\Users\juerz\AndroidStudioProjects\aulaplusapp\app\build\outputs\androidTest-results\connected\debug\Medium_Phone_API_36.1(AVD) - 16\device-info.pb"´

device-info.meminfoandroid‘
ŽC:\Users\juerz\AndroidStudioProjects\aulaplusapp\app\build\outputs\androidTest-results\connected\debug\Medium_Phone_API_36.1(AVD) - 16\meminfo"´

device-info.cpuinfoandroid‘
ŽC:\Users\juerz\AndroidStudioProjects\aulaplusapp\app\build\outputs\androidTest-results\connected\debug\Medium_Phone_API_36.1(AVD) - 16\cpuinfo­^
m
ScheduleScreenTestcom.example.prueba.ui.screensscheduleScreen_showsNextClass2Ù²ƒÊÀÆ”0:Ù²ƒÊ€‡°ˆ™W
µ+java.lang.RuntimeException: java.util.concurrent.ExecutionException: java.lang.RuntimeException: java.lang.NoSuchMethodException: android.hardware.input.InputManager.getInstance []
at androidx.test.espresso.Espresso.onIdle(Espresso.java:18)
at androidx.test.espresso.Espresso.onIdle(Espresso.java:1)
at androidx.compose.ui.test.junit4.EspressoLink_androidKt.runEspressoOnIdle(EspressoLink.android.kt:92)
at androidx.compose.ui.test.junit4.EspressoLink.runUntilIdle(EspressoLink.android.kt:79)
at androidx.compose.ui.test.AndroidComposeUiTestEnvironment.runTest(ComposeUiTest.android.kt:320)
at androidx.compose.ui.test.junit4.AndroidComposeTestRule$apply$1.evaluate(AndroidComposeTestRule.android.kt:271)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at org.junit.runners.Suite.runChild(Suite.java:128)
at org.junit.runners.Suite.runChild(Suite.java:27)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:67)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:58)
at androidx.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:446)
at android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:2627)
Caused by: java.util.concurrent.ExecutionException: java.lang.RuntimeException: java.lang.NoSuchMethodException: android.hardware.input.InputManager.getInstance []
at java.util.concurrent.FutureTask.report(FutureTask.java:122)
at java.util.concurrent.FutureTask.get(FutureTask.java:191)
at androidx.test.espresso.Espresso.onIdle(Espresso.java:12)
... 32 more
Caused by: java.lang.RuntimeException: java.lang.NoSuchMethodException: android.hardware.input.InputManager.getInstance []
at androidx.test.espresso.base.InputManagerEventInjectionStrategy.initialize(InputManagerEventInjectionStrategy.java:28)
at androidx.test.espresso.base.BaseLayerModule.provideEventInjector(BaseLayerModule.java:4)
at androidx.test.espresso.base.BaseLayerModule_ProvideEventInjectorFactory.provideEventInjector(BaseLayerModule_ProvideEventInjectorFactory.java:1)
at androidx.test.espresso.base.BaseLayerModule_ProvideEventInjectorFactory.get(BaseLayerModule_ProvideEventInjectorFactory.java:1)
at androidx.test.espresso.base.BaseLayerModule_ProvideEventInjectorFactory.get(BaseLayerModule_ProvideEventInjectorFactory.java:2)
at androidx.test.espresso.core.internal.deps.dagger.internal.DoubleCheck.get(DoubleCheck.java:6)
at androidx.test.espresso.base.UiControllerImpl_Factory.get(UiControllerImpl_Factory.java:1)
at androidx.test.espresso.base.UiControllerImpl_Factory.get(UiControllerImpl_Factory.java:2)
at androidx.test.espresso.core.internal.deps.dagger.internal.DoubleCheck.get(DoubleCheck.java:6)
at androidx.test.espresso.base.UiControllerModule_ProvideUiControllerFactory.get(UiControllerModule_ProvideUiControllerFactory.java:1)
at androidx.test.espresso.base.UiControllerModule_ProvideUiControllerFactory.get(UiControllerModule_ProvideUiControllerFactory.java:2)
at androidx.test.espresso.core.internal.deps.dagger.internal.DoubleCheck.get(DoubleCheck.java:6)
at androidx.test.espresso.DaggerBaseLayerComponent$BaseLayerComponentImpl.uiController(DaggerBaseLayerComponent.java:1)
at androidx.test.espresso.Espresso$1.run(Espresso.java:1)
at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:520)
at java.util.concurrent.FutureTask.run(FutureTask.java:317)
at android.os.Handler.handleCallback(Handler.java:1070)
at android.os.Handler.dispatchMessage(Handler.java:125)
at android.os.Looper.dispatchMessage(Looper.java:333)
at android.os.Looper.loopOnce(Looper.java:263)
at android.os.Looper.loop(Looper.java:367)
at android.app.ActivityThread.main(ActivityThread.java:9282)
at java.lang.reflect.Method.invoke(Native Method)
at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:566)
at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:929)
Caused by: java.lang.NoSuchMethodException: android.hardware.input.InputManager.getInstance []
at java.lang.Class.getMethod(Class.java:2934)
at java.lang.Class.getDeclaredMethod(Class.java:2913)
at androidx.test.espresso.base.InputManagerEventInjectionStrategy.initialize(InputManagerEventInjectionStrategy.java:5)
... 24 more'java.util.concurrent.ExecutionExceptionµ+java.lang.RuntimeException: java.util.concurrent.ExecutionException: java.lang.RuntimeException: java.lang.NoSuchMethodException: android.hardware.input.InputManager.getInstance []
at androidx.test.espresso.Espresso.onIdle(Espresso.java:18)
at androidx.test.espresso.Espresso.onIdle(Espresso.java:1)
at androidx.compose.ui.test.junit4.EspressoLink_androidKt.runEspressoOnIdle(EspressoLink.android.kt:92)
at androidx.compose.ui.test.junit4.EspressoLink.runUntilIdle(EspressoLink.android.kt:79)
at androidx.compose.ui.test.AndroidComposeUiTestEnvironment.runTest(ComposeUiTest.android.kt:320)
at androidx.compose.ui.test.junit4.AndroidComposeTestRule$apply$1.evaluate(AndroidComposeTestRule.android.kt:271)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at org.junit.runners.Suite.runChild(Suite.java:128)
at org.junit.runners.Suite.runChild(Suite.java:27)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:67)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:58)
at androidx.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:446)
at android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:2627)
Caused by: java.util.concurrent.ExecutionException: java.lang.RuntimeException: java.lang.NoSuchMethodException: android.hardware.input.InputManager.getInstance []
at java.util.concurrent.FutureTask.report(FutureTask.java:122)
at java.util.concurrent.FutureTask.get(FutureTask.java:191)
at androidx.test.espresso.Espresso.onIdle(Espresso.java:12)
... 32 more
Caused by: java.lang.RuntimeException: java.lang.NoSuchMethodException: android.hardware.input.InputManager.getInstance []
at androidx.test.espresso.base.InputManagerEventInjectionStrategy.initialize(InputManagerEventInjectionStrategy.java:28)
at androidx.test.espresso.base.BaseLayerModule.provideEventInjector(BaseLayerModule.java:4)
at androidx.test.espresso.base.BaseLayerModule_ProvideEventInjectorFactory.provideEventInjector(BaseLayerModule_ProvideEventInjectorFactory.java:1)
at androidx.test.espresso.base.BaseLayerModule_ProvideEventInjectorFactory.get(BaseLayerModule_ProvideEventInjectorFactory.java:1)
at androidx.test.espresso.base.BaseLayerModule_ProvideEventInjectorFactory.get(BaseLayerModule_ProvideEventInjectorFactory.java:2)
at androidx.test.espresso.core.internal.deps.dagger.internal.DoubleCheck.get(DoubleCheck.java:6)
at androidx.test.espresso.base.UiControllerImpl_Factory.get(UiControllerImpl_Factory.java:1)
at androidx.test.espresso.base.UiControllerImpl_Factory.get(UiControllerImpl_Factory.java:2)
at androidx.test.espresso.core.internal.deps.dagger.internal.DoubleCheck.get(DoubleCheck.java:6)
at androidx.test.espresso.base.UiControllerModule_ProvideUiControllerFactory.get(UiControllerModule_ProvideUiControllerFactory.java:1)
at androidx.test.espresso.base.UiControllerModule_ProvideUiControllerFactory.get(UiControllerModule_ProvideUiControllerFactory.java:2)
at androidx.test.espresso.core.internal.deps.dagger.internal.DoubleCheck.get(DoubleCheck.java:6)
at androidx.test.espresso.DaggerBaseLayerComponent$BaseLayerComponentImpl.uiController(DaggerBaseLayerComponent.java:1)
at androidx.test.espresso.Espresso$1.run(Espresso.java:1)
at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:520)
at java.util.concurrent.FutureTask.run(FutureTask.java:317)
at android.os.Handler.handleCallback(Handler.java:1070)
at android.os.Handler.dispatchMessage(Handler.java:125)
at android.os.Looper.dispatchMessage(Looper.java:333)
at android.os.Looper.loopOnce(Looper.java:263)
at android.os.Looper.loop(Looper.java:367)
at android.app.ActivityThread.main(ActivityThread.java:9282)
at java.lang.reflect.Method.invoke(Native Method)
at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:566)
at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:929)
Caused by: java.lang.NoSuchMethodException: android.hardware.input.InputManager.getInstance []
at java.lang.Class.getMethod(Class.java:2934)
at java.lang.Class.getDeclaredMethod(Class.java:2913)
at androidx.test.espresso.base.InputManagerEventInjectionStrategy.initialize(InputManagerEventInjectionStrategy.java:5)
... 24 more"ù

logcatandroidã
àC:\Users\juerz\AndroidStudioProjects\aulaplusapp\app\build\outputs\androidTest-results\connected\debug\Medium_Phone_API_36.1(AVD) - 16\logcat-com.example.prueba.ui.screens.ScheduleScreenTest-scheduleScreen_showsNextClass.txt"³

device-infoandroid˜
•C:\Users\juerz\AndroidStudioProjects\aulaplusapp\app\build\outputs\androidTest-results\connected\debug\Medium_Phone_API_36.1(AVD) - 16\device-info.pb"´

device-info.meminfoandroid‘
ŽC:\Users\juerz\AndroidStudioProjects\aulaplusapp\app\build\outputs\androidTest-results\connected\debug\Medium_Phone_API_36.1(AVD) - 16\meminfo"´

device-info.cpuinfoandroid‘
ŽC:\Users\juerz\AndroidStudioProjects\aulaplusapp\app\build\outputs\androidTest-results\connected\debug\Medium_Phone_API_36.1(AVD) - 16\cpuinfo*˜
c
test-results.logOcom.google.testing.platform.runtime.android.driver.AndroidInstrumentationDriver¢
ŸC:\Users\juerz\AndroidStudioProjects\aulaplusapp\app\build\outputs\androidTest-results\connected\debug\Medium_Phone_API_36.1(AVD) - 16\testlog\test-results.log 2
text/plain
package cn.axhl.xposedtest;

import java.lang.reflect.Method;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;

public class Fish2 implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        if (lpparam.packageName.equalsIgnoreCase("com.prgame5.fish2.baidu")) {

            Class<?>  DKOrderStatusClass = findClass("com.duoku.platform.single.item.DKOrderStatus", lpparam.classLoader);
            Class<?> DKOrderInfoDataClass = findClass("com.duoku.platform.single.item.DKOrderInfoData", lpparam.classLoader);
            findAndHookMethod("com.duoku.platform.single.d.a.e", lpparam.classLoader,
                    "a", int.class, DKOrderInfoDataClass, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            XposedBridge.log("i2 = " + param.args[0].toString() + "order = " + param.args[1].toString());

                            param.args[0] = 3010;
                            Object orderObj = param.args[1];
                            Class orderClzz =  orderObj.getClass();
                            //添加宏参数
                            Object[] enumConstants = DKOrderStatusClass.getEnumConstants();
                            Method setDkOrderStatus = orderClzz.getMethod("setDkOrderStatus", DKOrderStatusClass);
                            setDkOrderStatus.invoke(orderObj, enumConstants[3]);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                        }
                    });
        }
    }
}

package cn.axhl.xposedtest;
//一个测试
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class Doudizu implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equalsIgnoreCase("com.june.game.doudizhu")) {
            findAndHookMethod("com.alipay.sdk.app.PayTask", lpparam.classLoader,
                    "pay", String.class, boolean.class, new XC_MethodReplacement() {
                        @Override
                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                            return "resultStatus={9000};memo={支付成功};result={}";
                        }
                    }
            );
        }
    }
}

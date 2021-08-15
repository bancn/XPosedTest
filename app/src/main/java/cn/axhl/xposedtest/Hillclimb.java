package cn.axhl.xposedtest;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class Hillclimb implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equalsIgnoreCase("com.fingersoft.hillclimb.noncmcc")) {
            findAndHookMethod("com.mygamez.billing.BillingResult", lpparam.classLoader, "getResultCode",
                    new XC_MethodReplacement() {
                        @Override
                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                            return 1;
                        }
                    });
        }
    }
}

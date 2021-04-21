package com.wl.lib_aop.login_check;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wl.lib_aop.login_check.LoginCheck;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * AOP编程检查权限是否开启
 * AOP编程检查网络状态是否可用
 * AOP检查用户登录
 * AOP用户行为统计
 */
@Aspect
public class CheckLoginAspectJ {
    @Pointcut("execution(@com.wl.lib_aop.login_check.LoginCheck * *(..))")
    public void executionLoginCheck() {
    }

    //    @Before("executionLoginCheck()")
//    public void beforeMethodCall(ProceedingJoinPoint joinPoint){
//        Log.i("Aspect","beforeMethodCall");
//    }
//
//    @After("executionLoginCheck()")
//    public void afterMethodCall(ProceedingJoinPoint joinPoint){
//        Log.i("Aspect","afterMethodCall");
//    }
    @Before("execution(* com.wl.ui.BaseActivity.on**(..))")
    public void onActivityMethodBefore(JoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Context context = (Context) joinPoint.getTarget();
        if (context instanceof AppCompatActivity){
           String name= ((AppCompatActivity)context).getClass().getName();
            Log.i("AspectX", "className:"+name+" method:"+signature.getMethod().getName());
        }else {
            Log.i("AspectX",  "no name:"+signature.getMethod().getName());
        }

    }

    @Around("executionLoginCheck()")
    public Object loginCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Log.i("Aspect", "AroundMethodCall");
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        LoginCheck loginCheck = signature.getMethod().getAnnotation(LoginCheck.class);
        if (loginCheck != null) {
            Context context = (Context) proceedingJoinPoint.getTarget();
            Toast.makeText(context, "登陆检查", Toast.LENGTH_LONG).show();
        }
        return proceedingJoinPoint.proceed();
    }


}

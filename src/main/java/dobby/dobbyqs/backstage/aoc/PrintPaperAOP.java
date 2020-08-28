package dobby.dobbyqs.backstage.aoc;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.List;

//
//@Aspect
//@Component
public class PrintPaperAOP {

    @Pointcut(value = "execution(public String dobby.dobbyqs.backstage.controller.PrintController.answer(..))")
    public void pointCut() {
    }

    @Pointcut(value = "execution(public String dobby.dobbyqs.backstage.controller.BackPaperController.paper(..))")
    public void pointCut2() {
    }

    @Around("pointCut2()")
    public Object paperRandom(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        Object[] args = joinPoint.getArgs();
        System.out.println("------------------AOP-----------------------");
        for (Object o : args) {
            if (o instanceof Model) {
                System.out.println("------------------AOP-----------------------");
                Model model = (Model) o;
                Object a1 = model.getAttribute("A1");
                if (a1 != null && a1 instanceof List) {
                    List a1s = (List) a1;
                    a1s.sort((o1, o2) -> {
                        return o1.hashCode() - o2.hashCode();
                    });
                }

                Object a3 = model.getAttribute("A3");
                if (a3 != null && a3 instanceof List) {
                    List a3s = (List) a3;
                    a3s.sort((o1, o2) -> {
                        return o1.hashCode() - o2.hashCode();
                    });
                    for (Object oo : a3s) {
                        if (oo instanceof List) {
                            ((List) oo).sort((o1, o2) -> {
                                return o1.hashCode() - o2.hashCode();
                            });
                        }
                    }
                }

                Object b1 = model.getAttribute("B1");
                if (b1 != null && b1 instanceof List) {
                    List b1s = (List) b1;
                    b1s.sort((o1, o2) -> {
                        return o1.hashCode() - o2.hashCode();
                    });
                    for (Object oo : b1s) {
                        if (oo instanceof List) {
                            ((List) oo).sort((o1, o2) -> {
                                return o1.hashCode() - o2.hashCode();
                            });
                        }
                    }
                }
                break;
            }
        }
        return proceed;
    }

    @Around(value = "pointCut()")
    public Object random(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        Object[] args = joinPoint.getArgs();
        System.out.println("------------------AOP-----------------------");
        for (Object o : args) {
            if (o instanceof Model) {
                System.out.println("------------------AOP-----------------------");
                Model model = (Model) o;
                Object a1 = model.getAttribute("A1");
                if (a1 != null && a1 instanceof List) {
                    List a1s = (List) a1;
                    a1s.sort((o1, o2) -> {
                        return o1.hashCode() - o2.hashCode();
                    });
                }

                Object a3 = model.getAttribute("A3");
                if (a3 != null && a3 instanceof List) {
                    List a3s = (List) a3;
                    a3s.sort((o1, o2) -> {
                        return o1.hashCode() - o2.hashCode();
                    });
                    for (Object oo : a3s) {
                        if (oo instanceof List) {
                            ((List) oo).sort((o1, o2) -> {
                                return o1.hashCode() - o2.hashCode();
                            });
                        }
                    }
                }

                Object b1 = model.getAttribute("B1");
                if (b1 != null && b1 instanceof List) {
                    List b1s = (List) b1;
                    b1s.sort((o1, o2) -> {
                        return o1.hashCode() - o2.hashCode();
                    });
                    for (Object oo : b1s) {
                        if (oo instanceof List) {
                            ((List) oo).sort((o1, o2) -> {
                                return o1.hashCode() - o2.hashCode();
                            });
                        }
                    }
                }
                break;
            }
        }
        return proceed;
    }
}

package com.qiangxi;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.NestingKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic.Kind;

@AutoService(Processor.class)//这行代码是固定的,必须传递Processor.class
public class BindProcessor extends AbstractProcessor {
    private Filer mFiler;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnv.getFiler();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new LinkedHashSet<>();
        Set<Class<? extends Annotation>> annotations = getSupportedAnnotations();
        for (Class<? extends Annotation> annotation : annotations) {
            set.add(annotation.getCanonicalName());
        }
        return set;
    }

    /**
     * 存放所有自定义的注解,以后有新的注解也在这里添加
     */
    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(BindField.class);
        annotations.add(BindClass.class);
        annotations.add(BindMethod.class);
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        findAndParseTargets(roundEnvironment);
        return true;
    }

    private void findAndParseTargets(RoundEnvironment env) {
//        System.out.println("=====BindField========\n");
        //解析BindField注解
        Set<? extends Element> elements = env.getElementsAnnotatedWith(BindField.class);
        for (Element element : elements) {
            TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();
            Set<Modifier> modifiers = element.getModifiers();
            for (Modifier modifier : modifiers) {
//                log("modifier：" + modifier.toString());
            }
            String fieldType = element.asType().toString();
            VariableElement variableElement = (VariableElement) element;
//            log("字段类型：" + element.asType().toString());
//            log("字段类型：" +  variableElement.asType().toString());
//            log("字段值：" + variableElement.getConstantValue());
            String targetType = enclosingElement.getQualifiedName().toString();//原始类的类名
            String classPackage = getPackageName(enclosingElement);//原始类的包名
            String className = getClassName(enclosingElement, classPackage) + "Out";//输出类名
            Name targetName = element.getSimpleName();//注解所标记的字段名称
            BindField bindView = element.getAnnotation(BindField.class);//获取注解
            boolean value = bindView.value();//获取注解的属性值
//            log("原始类名：" + targetType);
//            log("原始包名：" + classPackage);
//            log("输出类名：" + className);
//            log("标记字段：" + targetName);
//            log("value：" + value);
//            generatorJavaFile(targetName + "Generator", value);
        }
        //解析BindClass注解
        System.out.println("=====BindClass========\n");
        Set<? extends Element> elements1 = env.getElementsAnnotatedWith(BindClass.class);
        for (Element element : elements1) {
            TypeElement enclosingElement;
            if (element instanceof TypeElement) {
                enclosingElement = (TypeElement) element;
            } else {
                enclosingElement = (TypeElement) element.getEnclosingElement();
            }
            ElementKind elementKind = enclosingElement.getKind();
            if (elementKind == ElementKind.INTERFACE) {
                //接口类
            } else if (elementKind == ElementKind.ANNOTATION_TYPE) {
                //注解类
            }
            log("当前类的类型：" + elementKind);
            NestingKind nestingKind = enclosingElement.getNestingKind();
            if (nestingKind == NestingKind.ANONYMOUS) {
                //匿名类
            } else if (nestingKind == NestingKind.MEMBER) {
                //内部类
            }
            log("NestingKind：" + nestingKind.toString());
            String superClass = enclosingElement.getSuperclass().toString();
            log("superClass：" + superClass);
            List<? extends TypeMirror> interfaces = enclosingElement.getInterfaces();
            for (TypeMirror anInterface : interfaces) {
                String implName = anInterface.toString();
                log("实现的接口：" + implName);
            }

            Set<Modifier> modifiers = enclosingElement.getModifiers();
            for (Modifier modifier : modifiers) {
                if (modifier == Modifier.ABSTRACT) {
                    //抽象类
                }
            }
            String targetType = enclosingElement.getQualifiedName().toString();//原始类的类名
            String classPackage = getPackageName(enclosingElement);//原始类的包名
            String className = getClassName(enclosingElement, classPackage) + "Out";//输出类名
            Name targetName = element.getSimpleName();//注解所标记的字段名称
//            BindClass annotation = enclosingElement.getAnnotation(BindClass.class);
            BindClass bindClass = element.getAnnotation(BindClass.class);//获取注解
            int value = bindClass.value();//获取注解的属性值
//            log("原始类名：" + targetType);
//            log("原始包名：" + classPackage);
//            log("输出类名：" + className);
//            log("标记字段：" + targetName);
//            log("value：" + value);
//            generatorJavaFile(targetName + "Generator", value);
        }
//        System.out.println("=====BindMethod========\n");
        //解析BindMethod注解
        Set<? extends Element> elements2 = env.getElementsAnnotatedWith(BindMethod.class);
        for (Element element : elements2) {
            Set<Modifier> modifiers = element.getModifiers();
            for (Modifier modifier : modifiers) {
//                log("modifier：" + modifier.toString());
            }
            ExecutableElement executableElement = (ExecutableElement) element;
            String returnType = executableElement.getReturnType().toString();
//            log("返回类型：" + returnType);
            List<? extends VariableElement> parameters = executableElement.getParameters();
            int paramsCount = parameters.size();
            for (VariableElement parameter : parameters) {
                String paramType = parameter.asType().toString();
//                log("参数类型：" + parameter.asType().toString());
            }
            TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();
            String targetType = enclosingElement.getQualifiedName().toString();//原始类的类名
            String classPackage = getPackageName(enclosingElement);//原始类的包名
            String className = getClassName(enclosingElement, classPackage) + "Out";//输出类名
            Name targetName = element.getSimpleName();//注解所标记的字段名称
            BindMethod bindMethod = element.getAnnotation(BindMethod.class);//获取注解
            String value = bindMethod.value();//获取注解的属性值
//            log("原始类名：" + targetType);
//            log("原始包名：" + classPackage);
//            log("输出类名：" + className);
//            log("标记字段：" + targetName);
//            log("value：" + value);
            generatorJavaFile(targetName + "Generator");
        }

    }

    /***********************************/

    //包名【输出文件时用】
    private String getPackageName(TypeElement type) {
        return processingEnv.getElementUtils().getPackageOf(type).getQualifiedName().toString();
    }

    //类名【输出文件时用】
    private static String getClassName(TypeElement type, String packageName) {
        int packageLen = packageName.length() + 1;
        return type.getQualifiedName().toString().substring(packageLen).replace('.', '$');
    }

    private void generatorJavaFile(String fileName) {

        MethodSpec method = MethodSpec.methodBuilder("idMethod")
                .returns(TypeName.VOID)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(TypeName.INT, "value")
                .addStatement("  android.util.Log.e(\"tag\", \"value=\" + value);")
                .build();
        TypeSpec clazz = TypeSpec.classBuilder(fileName)
                .addModifiers(Modifier.PUBLIC)
                .addMethod(method)
                .build();
        JavaFile javaFile = JavaFile.builder("com.qiangxi.activity", clazz)
                .addFileComment(Constants.TIP)
                .build();
        try {
            javaFile.writeTo(mFiler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void log(String message) {
        System.out.println(message);
    }

    /**
     * 打印错误日志
     */
    private void logE(Element element, String message, Object... args) {
        print(Kind.ERROR, element, message, args);
    }

    /**
     * 打印正常日志
     */
    private void logI(Element element, String message, Object... args) {
        print(Kind.NOTE, element, message, args);
    }

    private void print(Kind kind, Element element, String message, Object... args) {
        if (args.length > 0) {
            message = String.format(message, args);
        }
        processingEnv.getMessager().printMessage(kind, message, element);
    }
}

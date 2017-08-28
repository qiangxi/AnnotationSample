package com.qiangxi;

import com.google.auto.service.AutoService;

import java.io.PrintWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;


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
        annotations.add(BindView.class);
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindView.class);
        for (Element element : elements) {
            Name targetName = element.getSimpleName();//注解所标记的字段名称
            BindView bindView = element.getAnnotation(BindView.class);//获取注解
            int value = bindView.value();//获取注解的属性值

            generatorJavaFile(targetName + "Generator");
        }
        return true;
    }

    private void generatorJavaFile(String fileName) {
        try {
            JavaFileObject javaFileObject = mFiler.createSourceFile(fileName);
            Writer writer = javaFileObject.openWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            printWriter.println(Constants.TIP);
            printWriter.flush();
            printWriter.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

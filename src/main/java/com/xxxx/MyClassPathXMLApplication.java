package com.xxxx;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模拟spring实现
 * 1。通过带参数构造器得到对应的配置文件
 * 2。通过dom4j解析xml文件得到list集合，存放MyBean对象列表
 * 3。遍历list集合，通过反射实例化对象，放置在map中
 * 4。通过id，获取指定的实例化对象
 */
public class MyClassPathXMLApplication  implements MyFactory{
    private List<MyBean> beanList = new ArrayList<>();
    private Map<String,Object> beanMap = new HashMap<>();
    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }

    public MyClassPathXMLApplication(String fileName) {
            this.parseXml(fileName);
        try {
            this.instanceBean();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println("beanlist:"+beanList.size());
        System.out.println("benmap:"+beanMap.size());
    }

    private void instanceBean() throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        if (beanList==null ||beanList.size()==0)return;
        for (MyBean myBean : beanList) {
                Constructor<?> constructor = Class.forName(myBean.getClazz()).getDeclaredConstructor();
                constructor.setAccessible(true);
                Object o = constructor.newInstance();
                beanMap.put(myBean.getId(), o);
        }
    }

    /**
     * 通过dom4j解析xml文件得到list集合，存放MyBean对象列表
     * 1.获取解析器
     * 2。获取配置文件路径
     * 3。通过解析器解析配置文件
     * 4。通过xpath语法解析，获取beans标签下所有的bean
     * 5。通过指定的解析语法解析文档对象 返回元素集合
     * 6。判断元素集合是否为空
     * 7。如果不为空，遍历集合
     * 8。获取bean标签中的对应属性：id，class
     * 9。获取myBean对象， 将ID和class设置到myBean中，myBean存储到list中
     *
     * @param fileName
     */
    private void parseXml(String fileName) {
        //     1.获取解析器
        SAXReader saxReader = new SAXReader();
        //      2。获取配置文件路径
        URL url = this.getClass().getClassLoader().getResource(fileName);
        //      3。通过解析器解析配置文件

        try {
            //3。通过解析器解析配置文件
            Document document = saxReader.read(url);
            //4。通过xpath语法解析，获取beans标签下所有的bean
            XPath xPath = document.createXPath("Beans/Bean");
            //5。通过指定的解析语法解析文档对象 返回元素集合
            List<Element> elements = xPath.selectNodes(document);

            if (elements!=null && elements.size()>0){
                for (Element  e:elements) {
                    String id = e.attributeValue("id");
                    String clazz = e.attributeValue("class");
                    MyBean myBean = new MyBean(id, clazz);
                    beanList.add(myBean);
                }
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }
}

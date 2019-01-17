/**
 * The MIT License
 * Copyright (c) 2014-2016 Ilkka Seppälä
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR Sample PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.gnuhpc.bigdata.designpattern.abstractdocument;


import lombok.extern.slf4j.Slf4j;
import org.gnuhpc.bigdata.designpattern.abstractdocument.document.Document;
import org.gnuhpc.bigdata.designpattern.abstractdocument.attribute.HasModel;
import org.gnuhpc.bigdata.designpattern.abstractdocument.attribute.HasParts;
import org.gnuhpc.bigdata.designpattern.abstractdocument.attribute.HasPrice;
import org.gnuhpc.bigdata.designpattern.abstractdocument.attribute.HasType;
import org.gnuhpc.bigdata.designpattern.abstractdocument.entity.Car;
import org.gnuhpc.bigdata.designpattern.abstractdocument.abstractdocument.AbstractDocument;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**  抽象文档模式处理多个不确定的，非静态的属性,方便组织属性
 *
 * 每个属性是一个document接口，因此有get set 甚至是child这样的嵌套属性
 * 而 abstractdocument 是对document的一个实现
 * 另外，对于属性的分类则是attribute，是一系列继承document接口，其中有代表此类属性的一个properties字符串
 * 有些属性是带有嵌套性质的，比如HasParts。这些具体的属性都提供了一些默认实现。
 * 实体类则是继承了abstractdocument
 * The Abstract Document pattern enables handling additional, non-static
 * properties. This pattern uses concept of traits to enable type safety and
 * separate properties of different classes into set of interfaces.
 * <p>
 * <p>
 * In Abstract Document pattern,({@link AbstractDocument}) fully implements
 * {@link Document}) interface. Traits are then defined to enable access to
 * properties in usual, static way.
 */
@Slf4j
public class App {

  /**
   * Executes the App
   */
  public App() {
    log.info("Constructing parts and car");

    Map<String, Object> carProperties = new HashMap<>();
    carProperties.put(HasModel.PROPERTY, "300SL");
    carProperties.put(HasPrice.PROPERTY, 10000L);

    Map<String, Object> wheelProperties = new HashMap<>();
    wheelProperties.put(HasType.PROPERTY, "wheel");
    wheelProperties.put(HasModel.PROPERTY, "15C");
    wheelProperties.put(HasPrice.PROPERTY, 100L);

    Map<String, Object> doorProperties = new HashMap<>();
    doorProperties.put(HasType.PROPERTY, "door");
    doorProperties.put(HasModel.PROPERTY, "Lambo");
    doorProperties.put(HasPrice.PROPERTY, 300L);

    carProperties.put(HasParts.PROPERTY, Arrays.asList(wheelProperties, doorProperties));

    Car car = new Car(carProperties);

    log.info("Here is our car:");
    log.info("-> model: {}", car.getModel().get());
    log.info("-> price: {}", car.getPrice().get());
    log.info("-> parts: ");
    car.getParts().forEach(p -> log.info("\t{}/{}/{}", p.getType().get(), p.getModel().get(), p.getPrice().get()));
  }

  /**
   * Program entry point
   *
   * @param args command line args
   */
  public static void main(String[] args) {
    new App();
  }

}

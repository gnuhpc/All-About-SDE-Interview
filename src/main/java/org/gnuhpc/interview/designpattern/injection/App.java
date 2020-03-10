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
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.gnuhpc.interview.designpattern.injection;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Dependency Injection pattern deals with how objects handle their dependencies. The pattern
 * implements so called inversion of control principle. Inversion of control has two specific rules:
 * - High-level modules should not depend on low-level modules. Both should depend on abstractions.
 * - Abstractions should not depend on details. Details should depend on abstractions.
 * <p>
 * The fourth example takes the pattern a step further. It uses Guice framework for Dependency
 * Injection. {@link TobaccoModule} binds a concrete implementation to abstraction. Injector is then
 * used to create {@link GuiceWizard} object with correct dependencies.
 */
public class App {

    /**
     * Program entry point
     *
     * @param args command line args
     */
    public static void main(String[] args) {

        AdvancedWizard advancedWizard = new AdvancedWizard(new SecondBreakfastTobacco());
        advancedWizard.smoke();

        Injector injector = Guice.createInjector(new TobaccoModule());
        GuiceWizard guiceWizard = injector.getInstance(GuiceWizard.class);
        guiceWizard.smoke();
    }
}

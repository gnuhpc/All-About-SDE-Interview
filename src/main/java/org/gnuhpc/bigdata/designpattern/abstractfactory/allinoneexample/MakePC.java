package org.gnuhpc.bigdata.designpattern.abstractfactory.allinoneexample;

// product
// class CPU
abstract class CPU {}

//concrete product
// class AMDCPU
class AMDCPU extends CPU {}

//concrete product
// class IntelCPU
class IntelCPU extends CPU {}

// product
// class MMU
abstract class MMU {}

//concrete product
// class AMDMMU
class AMDMMU extends MMU {}

//concrete product
// class IntelMMU
class IntelMMU extends MMU {}


//concrete factory
// class AMDFactory
class AMDFactory extends AbstractFactory {
    @Override
    public CPU createCPU() {
        return new AMDCPU();
    }

    @Override
    public MMU createMMU() {
        return new AMDMMU();
    }
}

//concrete factory
// class IntelFactory
class IntelFactory extends AbstractFactory {
    @Override
    public CPU createCPU() {
        return new IntelCPU();
    }

    @Override
    public MMU createMMU() {
        return new IntelMMU();
    }
}

// product type
enum Architecture {
    Intel, AMD
}

//Factory
abstract class AbstractFactory {
    private static final AMDFactory AMD_TOOLKIT = new AMDFactory();
    private static final IntelFactory Intel_TOOLKIT = new IntelFactory();

    // Returns a concrete factory object that is an instance of the
    // concrete factory class appropriate for the given architecture.
    static AbstractFactory getFactory(Architecture architecture) {
        AbstractFactory factory = null;
        switch (architecture) {
            case Intel:
                factory = Intel_TOOLKIT;
                break;
            case AMD:
                factory = AMD_TOOLKIT;
                break;
        }
        return factory;
    }

    public abstract CPU createCPU();

    public abstract MMU createMMU();
}

public class MakePC{
    public static void main(String[] args) {
        AbstractFactory factory = AbstractFactory.getFactory(Architecture.AMD);
        CPU cpu = factory.createCPU();
    }
}


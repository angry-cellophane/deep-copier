import org.dcopier.util.OneField

def o = new Object()
def f1 = new OneField(object: o)
def f2 = new OneField(object: o)
assert f1 == f2

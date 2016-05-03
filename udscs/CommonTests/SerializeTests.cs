using Microsoft.VisualStudio.TestTools.UnitTesting;
using Uds.Common;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace Common.Tests
{
    [TestClass()]
    public class SerializeTests
    {
        public class A
        {
            private int PrivateMember;
            public int IncrementPrivateMember()
            {
                return ++PrivateMember;
            }
            public int PublicMember;
            public A()
            {
                PrivateMember = 0;
                PublicMember = 0;
            }
        }

        [Serializable]
        public class SerializableA : A { }

        public class NonSerializableA : A { }

        [TestMethod()]
        public void Serailaize_Serializable_Class_Test()
        {
            SerializableA a = new SerializableA();
            a.PublicMember = 10;
            var incrementedPrivateMember = a.IncrementPrivateMember();
            var stream = Serialize<SerializableA>.SerializeToXmlStream(a);
        }

        [TestMethod()]
        public void Serailaize_NonSerializable_Class_Test()
        {
            bool argumentExceptionThrown = false;
            NonSerializableA a = new NonSerializableA();
            a.PublicMember = 10;
            var incrementedPrivateMember = a.IncrementPrivateMember();
            try
            {
                var stream = Serialize<NonSerializableA>.SerializeToXmlStream(a);
            }
            catch (ArgumentException ex)
            {
                argumentExceptionThrown = true;
            }
            Assert.IsTrue(argumentExceptionThrown);
        }

        [TestMethod()]
        public void Serializ_And_Deserialize_Serializable_Class()
        {
            SerializableA a = new SerializableA();
            a.PublicMember = 20;
            a.IncrementPrivateMember();
            var i = a.IncrementPrivateMember();
            Stream s = Serialize<SerializableA>.SerializeToXmlStream(a);
            var copyOfA = Serialize<SerializableA>.DeserializeFromXmlStream(s);
            Assert.AreEqual(a.PublicMember, copyOfA.PublicMember);
            i = a.IncrementPrivateMember();
            var i_copy = copyOfA.IncrementPrivateMember();
            Assert.AreEqual(i, i_copy);
        }
    }
}
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Uds.Server;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Uds.Server.Tests
{
    [TestClass()]
    public class ServerTests
    {
        [TestMethod()]
        public void ServerTest()
        {
            Assert.Fail();
        }

        [TestMethod()]
        public void StartTest()
        {
            Server server = new Server();
            server.Start();
            Assert.Fail();
        }

        [TestMethod()]
        public void StopTest()
        {
            Assert.Fail();
        }
    }
}
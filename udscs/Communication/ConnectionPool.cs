using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Uds.Communication
{
    public class ConnectionPool : List<Connection>
    {
        public ConnectionPool()
        {
        }

        public int ActiveCount()
        {
            throw new NotImplementedException();
        }
    }
}

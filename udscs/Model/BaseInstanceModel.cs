using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace UDS.Framework.Model
{
    public abstract class BaseInstanceModel
    {
        private Guid LocalId;

        public Guid Id
        {
            get { return LocalId; }
            set { LocalId = value; }
        }

        private string LocalName;

        public string Name
        {
            get { return LocalName; }
            set { LocalName = value; }
        }

        private IPEndPoint LocalAddress;

        public IPEndPoint Address
        {
            get { return LocalAddress; }
            set { LocalAddress = value; }
        }

    }
}


const fs = require('fs')

fs.readFile('./BeancurdV.class', null, (err, data) => {
  if (err) {
    console.error(err);
    return;
  }
i
  // 打印Buffer数组中的信息
  // for (const i in data) {
  //   console.log(i);
  // }

  var list = [];

  var len = data.length;
  for (var i=0; i<len; i++)
  {
//    console.log(data[i]);
    var formatR = data[i].toString(16);
    if(formatR.length == 1) {
      formatR = '0' + formatR  
    }

    list.push(formatR);

//     if(list.length == 40) {
//       console.log(list.toString());
//       list = [];
//     }
  }

  if(list.length > 0) {
    console.log(list.toString());
    list = []; 
  }
});
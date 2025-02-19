package hw2.huge_integer;

public class HugeInteger{
    private int[] digits = new int[40];
    private int sign;
    private int len;

    public HugeInteger(){
        this("0");
    }
    public HugeInteger(String s){
        parse(s);
    }
    
    private void parse(String s){
        len = s.length();
        int ptr = 0;
        if (s.charAt(0) == '-') {
            sign = -1;
        }
        for (int i = len - 1; i >= 0; i--) {
            char c = s.charAt(ptr);
            ptr++;
            if (c == '-')
                continue;
            digits[i] = Character.getNumericValue(c);
        }
        if(sign == -1){
            len--;
        }
    }
    public String toString(){
        String s = "";
        for(int i = 0;i<len;i++){
            s = Character.forDigit(digits[i],10) + s;
        }
        if(sign < 0){
            s = '-' +  s;
        }
        return s;
    }
    public HugeInteger add(HugeInteger addend){
        HugeInteger lastresult;
        String result = "";
        int carry = 0;
        if(sign == addend.sign){
            for(int i = 0;i<Math.max(len,addend.len);i++){
                int num = (digits[i] + addend.digits[i] + carry)%10;
                carry = (digits[i] + addend.digits[i] + carry)/10;
                result = Character.forDigit(num,10) + result;
            }
            if(carry != 0){
                result = Character.forDigit(carry, 10) + result;
            }
            if(sign<0){
                result = "-" + result;
            }
            lastresult = new HugeInteger(result);
        }
        else{
            if(sign < 0){
                lastresult = addend.subtract(this.abs());
            }
            else{
                // System.out.println(addend.abs().toString());
                lastresult = this.subtract(addend.abs());
            }
        }
        return  lastresult;
    }

    public HugeInteger subtract(HugeInteger subtractend){
        // System.out.println(this.toString()+" "+subtractend.toString());
        HugeInteger result = new HugeInteger();
        if(subtractend.sign < 0){
            result = this.add(subtractend.abs());
        }
        else if(sign < 0){
            result = subtractend.add(this.abs());
            result = new HugeInteger("-"+ result.toString());
        }
        else{//正常減法
            if(this.isEqualTo(subtractend)){
                return new HugeInteger("0");
            }
            if(this.isGreaterThan(subtractend)){//a-b a>b
                // System.out.println("ok\n");
                int carry = 0;//向上一位借位
                String s = "";
                for(int i = 0 ;i<len;i++){
                    if((digits[i]-carry) < subtractend.digits[i]){
                        s = Character.forDigit(digits[i] - carry + 10 - subtractend.digits[i], 10) + s;
                        // System.out.println(digits[i] + "-" + carry + "<" + subtractend.digits[i]);
                        carry = 1;
                    }
                    else{
                        s = Character.forDigit(digits[i] - carry - subtractend.digits[i], 10) + s;
                        // System.out.println("ok\n");
                        carry = 0;
                    }
                }
                result = new HugeInteger(s);
            }
            else{
                result = subtractend.subtract(this);
                result = new HugeInteger("-" + result.toString());
            }
        }
        return  result;
    }

    public boolean isEqualTo(HugeInteger other){
        if(len != other.len){
            return false;
        }
        if(sign != other.sign){
            return false;
        }
        for(int i = 0;i<len;i++){
            if(digits[i] != other.digits[i]){
                return  false;
            }
        }
        return true;
    }

    public boolean isNotEqualTo(HugeInteger other){
        return !isEqualTo(other);
    }

    public boolean isGreaterThan(HugeInteger other){
        // System.out.println(this.toString()+" "+other.toString());
        if (sign > other.sign){
            // System.out.println("ok0\n");
            return true;
        }
        else if (sign < other.sign){
            // System.out.println("ok1\n");
            return false;
        }
        else if (sign >= 0) {
            if (len > other.len){
                // System.out.println("ok2\n");
                return true;
            }
            else if (len < other.len){
                // System.out.println("ok3\n");
                return false;
            }
            else {
                for (int i = len - 1; i >= 0; i--) {
                    if (digits[i] > other.digits[i]){
                        // System.out.println("ok4\n");
                        return true;
                    }
                }
                // System.out.println("ok4\n");
                return false;
            }
        } else {
            if (len > other.len){
                // System.out.println("ok5\n");
                return false;
            }
            else if (len < other.len){
                // System.out.println("ok6\n");
                return true;
            }
            else {
                for (int i = len - 1; i >= 0; i--) {
                    if (digits[i] < other.digits[i]){
                        // System.out.println("ok7\n");
                        return true;
                    }
                }
                // System.out.println("ok8\n");
                return false;
            }
        }
    }

    public boolean isGreaterThanOrEqualTo(HugeInteger other) {
        return isGreaterThan(other) || isEqualTo(other);
    }

    public boolean isLessThanOrEqualTo(HugeInteger other) {
        return isLessThan(other) || isEqualTo(other);
    }

    public boolean isLessThan(HugeInteger other) {
        return !isGreaterThanOrEqualTo(other);
    }

    public boolean isZero() {
        for (int i = 0; i < len; i++) {
            if (digits[i] != 0) {
                return false;
            }
        }
        return true;
    }

    private HugeInteger abs(){
        String temp = this.toString();
        if(sign == -1){
            temp = temp.substring(1);
        }
        HugeInteger result = new HugeInteger(temp);
        // System.out.println(result.toString());
        return result;
    }

    public HugeInteger multiply(HugeInteger other){
        String Str1 = toString();
        String Str2 = other.toString();
        int[] multstr = new int[Str1.length() + Str2.length()];

        // Calculate the result of each index in the multstr array.
        for (int i = Str1.length() - 1; i >= 0; i--) {
            for (int j = Str2.length() - 1; j >= 0; j--) {
                int pos1 = i + j, pos2 = i + j + 1;
                int mul = (Str1.charAt(i) - '0') * (Str2.charAt(j) - '0');
                // The current sum equals mul plus the ten's digit from the last mul 
                // (multstr[pos2], or you can find from the draft directly, they are in the same column)
                int sum = mul + multstr[pos2];
                
                multstr[pos1] += sum / 10;
                multstr[pos2] = sum % 10;
            }
        }

        // Turn the multstr array into String result
        StringBuilder result = new StringBuilder();
        for (int p : multstr) {
            if (!(result.length() == 0 && p == 0)) {
                result.append(p);
            }
        }
        return new HugeInteger(result.toString());
    }
    // public HugeInteger divide(HugeInteger other,int action){
    //     int rsign = 0;
    //     if(sign != other.sign){
    //         rsign = -1;
    //     }
    //     if(isLessThan(other)){
    //         if(action == 0){//divide
    //             return new HugeInteger("0");
    //         }
    //         else{//remain
    //             return other;
    //         }
    //     }
    //     else{
    //         String str2 = other.abs().toString();
    //         String temp = str2.substring(0,str2.length()-1);
    //         HugeInteger dividend = new HugeInteger(temp);
    //         int[] div = new int[40];
    //         int digitcount = 0;
    //         for(int i = str2.length();i<=len;i++){
    //             String stemp;
    //             int count = 0;
    //             while(!(other.isGreaterThan(dividend))){
    //                 dividend = dividend.subtract(other);
    //                 count++;
    //             }
    //             div[digitcount] = count;
    //             digitcount++;
    //             if(i != len){
    //                 stemp = dividend.toString();
    //                 stemp += dividend.digits[i];
    //                 dividend = new HugeInteger(stemp);
    //             }
    //         }
    //         if(action == 1){
    //             return dividend;
    //         }
    //         int check = 0;
    //         String result = "";
    //         for(int i = 0;i<digitcount;i++){
    //             if(div[i] != 0){
    //                 check = 1;
    //             }
    //             if(check == 0){
    //                 digitcount++;
    //                 continue;
    //             }
    //             result += div[i];
    //         }
    //     }
    //     return new HugeInteger("0");
    // }
}
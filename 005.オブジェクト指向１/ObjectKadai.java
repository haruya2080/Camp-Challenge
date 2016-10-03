/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.camp.objectkadai;

import static java.lang.System.out;

/**
 *
 * @author niwayama
 */
public class ObjectKadai {
    // コンストラクタ
    public ObjectKadai () {}
    
    /**
     * 課題対象のテストクラス
     */
    public class TestClass {
        private int num;    
        private String text;
        
        // コンストラクタ
        public TestClass () {}

        /**
         * @param num the num to set
         */
        public void setNum(int num) {
            this.num = num;
        }

        /**
         * @param text the text to set
         */
        public void setText(String text) {
            this.text = text;
        }

        public void printNum () {
            out.print(num);
        }

        public void printText () {
            out.print(text);
        }

        public void clearNum () {
            num = 0;
        }
        
        public void clearText () {
            text = null;
        }
    }
    
    /**
     * 課題対象の継承を扱うテストクラス
     */
    
    public class TestExtends extends TestClass {
        @Override
        public void clearNum () {
            super.num = 0;
        }
        
        @Override
        public void clearText () {
            super.text = null;
        }
    }
}

//
//  AboutViewController.swift
//  BullsEye_lfriedl
//
//  Created by Friedl, Luke on 9/4/18.
//  Copyright © 2018 CVTC. All rights reserved.
//

import UIKit
import WebKit

class AboutViewController: UIViewController {
    
    // MARK: - @IBOutlets
    
    @IBOutlet weak var AboutWebView: WKWebView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        if let url = Bundle.main.url(forResource: "BullsEye", withExtension: "html") {
            if let htmlData = try? Data(contentsOf: url) {
                let baseURL = URL(fileURLWithPath: Bundle.main.bundlePath)
                AboutWebView.load(htmlData, mimeType: "text/html",
                                  characterEncodingName: "UTF-8",
                                  baseURL: baseURL)
            }
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    
    @IBAction func close(_ sender: UIButton) {
        dismiss(animated: true, completion: nil)
    }
    

}
